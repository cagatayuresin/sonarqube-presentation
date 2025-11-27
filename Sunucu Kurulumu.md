# SonarQube Kubernetes Kurulumu

Bu dokümanda SonarQube'u K3s Kubernetes cluster üzerinde, Let's Encrypt SSL sertifikası ile birlikte kurulum adımları anlatılmaktadır.

## 📋 Gereksinimler

- Linux sunucu (Ubuntu/Debian önerilir)
- DNS A kaydı (Domain → Sunucu IP)
- Root/sudo erişimi
- En az 4GB RAM

---

## 🔧 1. Sistem Ayarları

SonarQube için Elasticsearch gereksinimleri:

```bash
# Anlık olarak ayarla
sudo sysctl -w vm.max_map_count=262144
sudo sysctl -w fs.file-max=65536

# Kalıcı hale getir (Reboot sonrası da geçerli)
echo "vm.max_map_count=262144" | sudo tee -a /etc/sysctl.conf
echo "fs.file-max=65536" | sudo tee -a /etc/sysctl.conf
```

---

## ☸️ 2. K3s Kurulumu

Hafif Kubernetes dağıtımı:

```bash
curl -sfL https://get.k3s.io | sh -
```

Cluster durumunu kontrol et:

```bash
sudo k3s kubectl get nodes
```

---

## 📦 3. Helm Kurulumu

Kubernetes paket yöneticisi:

```bash
curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
```

---

## 🔐 4. Cert-Manager Kurulumu

Let's Encrypt SSL sertifikası için:

```bash
helm repo add jetstack https://charts.jetstack.io
helm repo update
helm install cert-manager jetstack/cert-manager \
  --namespace cert-manager \
  --create-namespace \
  --set crds.enabled=true
```

---

## 📝 5. Let's Encrypt Issuer Yapılandırması

`cluster-issuer.yaml` dosyası oluştur:

```yaml
apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: letsencrypt-prod
spec:
  acme:
    server: https://acme-v02.api.letsencrypt.org/directory
    email: cagatayuresin@gmail.com  # Kendi email adresinizi yazın
    privateKeySecretRef:
      name: letsencrypt-prod
    solvers:
    - http01:
        ingress:
          class: traefik
```

Uygula:

```bash
kubectl apply -f cluster-issuer.yaml
```

---

## 🚀 6. SonarQube Stack Deployment

`sonarqube-stack.yaml` dosyası oluştur:

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: sonarqube
---
# --- POSTGRESQL BÖLÜMÜ ---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: postgres-data
  namespace: sonarqube
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: postgresql
  namespace: sonarqube
spec:
  selector:
    matchLabels:
      app: postgresql
  template:
    metadata:
      labels:
        app: postgresql
    spec:
      containers:
      - name: postgresql
        image: postgres:15
        env:
        - name: POSTGRES_USER
          value: sonar
        - name: POSTGRES_PASSWORD
          value: sonarpass  # Production'da güçlü şifre kullanın!
        - name: POSTGRES_DB
          value: sonar
        ports:
        - containerPort: 5432
        volumeMounts:
        - mountPath: /var/lib/postgresql/data
          name: postgres-data
      volumes:
      - name: postgres-data
        persistentVolumeClaim:
          claimName: postgres-data
---
apiVersion: v1
kind: Service
metadata:
  name: postgresql
  namespace: sonarqube
spec:
  selector:
    app: postgresql
  ports:
    - port: 5432
---
# --- SONARQUBE BÖLÜMÜ ---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: sonarqube-data
  namespace: sonarqube
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: sonarqube
  namespace: sonarqube
spec:
  replicas: 1
  selector:
    matchLabels:
      app: sonarqube
  template:
    metadata:
      labels:
        app: sonarqube
    spec:
      containers:
      - name: sonarqube
        image: sonarqube:community
        ports:
        - containerPort: 9000
        env:
        - name: SONAR_JDBC_URL
          value: jdbc:postgresql://postgresql:5432/sonar
        - name: SONAR_JDBC_USERNAME
          value: sonar
        - name: SONAR_JDBC_PASSWORD
          value: sonarpass
        volumeMounts:
        - mountPath: /opt/sonarqube/data
          name: sonarqube-data
          subPath: data
        - mountPath: /opt/sonarqube/extensions
          name: sonarqube-data
          subPath: extensions
        - mountPath: /opt/sonarqube/logs
          name: sonarqube-data
          subPath: logs
        resources:
          requests:
            memory: "1Gi"
            cpu: "500m"
          limits:
            memory: "3Gi"
            cpu: "2000m"
      volumes:
      - name: sonarqube-data
        persistentVolumeClaim:
          claimName: sonarqube-data
---
apiVersion: v1
kind: Service
metadata:
  name: sonarqube
  namespace: sonarqube
spec:
  selector:
    app: sonarqube
  ports:
    - port: 9000
      targetPort: 9000
---
# --- INGRESS VE SSL ---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: sonarqube-ingress
  namespace: sonarqube
  annotations:
    cert-manager.io/cluster-issuer: "letsencrypt-prod"
    kubernetes.io/ingress.class: "traefik"
spec:
  tls:
  - hosts:
    - sonarqube.cagatayuresin.com  # Kendi domain'inizi yazın
    secretName: sonarqube-tls-secret
  rules:
  - host: sonarqube.cagatayuresin.com  # Kendi domain'inizi yazın
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: sonarqube
            port:
              number: 9000
```

Uygula:

```bash
kubectl apply -f sonarqube-stack.yaml
```

---

## ✅ 7. Kontroller

Pod'ların durumunu izle:

```bash
kubectl get pods -n sonarqube -w
```

SSL sertifikasını kontrol et:

```bash
kubectl get certificate -n sonarqube
```

Tüm kaynakları görüntüle:

```bash
kubectl get all -n sonarqube
```

---

## 🌐 8. Erişim

- **URL**: https://sonarqube.cagatayuresin.com
- **Kullanıcı**: admin
- **Şifre**: admin

> ⚠️ İlk girişte şifre değiştirmeniz istenecektir.

---

## 🔍 Sorun Giderme

### Pod'lar başlamıyor

```bash
# Logları kontrol et
kubectl logs -n sonarqube deployment/sonarqube
kubectl logs -n sonarqube deployment/postgresql

# Pod detaylarını görüntüle
kubectl describe pod -n sonarqube <pod-name>
```

### SSL sertifikası oluşmadı

```bash
# Cert-manager loglarını kontrol et
kubectl logs -n cert-manager deployment/cert-manager

# Certificate durumunu detaylı incele
kubectl describe certificate -n sonarqube sonarqube-tls-secret
```

### DNS sorunları

- DNS A kaydının sunucu IP'sine doğru yönlendiğinden emin olun
- `nslookup sonarqube.cagatayuresin.com` ile kontrol edin

---

## 🗑️ Temizlik

Tüm kaynakları kaldırmak için:

```bash
kubectl delete namespace sonarqube
kubectl delete clusterissuer letsencrypt-prod
```