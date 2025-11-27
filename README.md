# SonarQube Sunum Projesi

Yazılım Güvenliği ve Analizi dersi için hazırlanmış SonarQube demo ve sunum projesi.

## 📁 Proje Yapısı

```
sonarqube-presentation/
├── demo-projects/          # Örnek hatalı kodlar
│   ├── python-app.py      # Python güvenlik açıkları
│   ├── CSharpApp.cs       # C# güvenlik açıkları
│   └── javascript-app.js  # JavaScript güvenlik açıkları
├── docker-compose.yml     # Lokal SonarQube kurulumu
└── Sunucu Kurulumu.md     # K8s production kurulum dokümanı
```

## 🚀 Hızlı Başlangıç

### Lokal Docker Kurulumu

```bash
docker-compose up -d
```

**Erişim:**
- URL: http://localhost:9000
- Kullanıcı: admin
- Şifre: admin

### Production Kubernetes Kurulumu

Detaylı kurulum adımları için: [Sunucu Kurulumu.md](./Sunucu%20Kurulumu.md)

## 📊 Demo Projeler

`demo-projects/` klasöründe kasıtlı güvenlik açıkları içeren örnek kodlar bulunmaktadır:

- **Python** - SQL injection, hardcoded secrets, code smells
- **C#** - Güvenlik açıkları, null reference, weak crypto
- **JavaScript** - eval(), command injection, callback hell

Bu kodlar SonarQube'da çeşitli bulguları göstermek için hazırlanmıştır.

## 🔍 SonarQube Analizi

### GitHub Actions ile

GitHub repository'nize SonarQube analizi eklemek için:

1. SonarQube'da proje token'ı oluşturun
2. GitHub Secrets'a `SONAR_TOKEN` ekleyin
3. `.github/workflows/sonarqube.yml` oluşturun

### Manuel Analiz

```bash
docker run --rm \
  --network sonarqube-presentation_sonar_net \
  -v "$(pwd):/usr/src" \
  -w /usr/src \
  sonarsource/sonar-scanner-cli \
  -Dsonar.projectKey=demo-project \
  -Dsonar.sources=. \
  -Dsonar.host.url=http://sonarqube:9000 \
  -Dsonar.login=YOUR_TOKEN
```

## 🎓 Sunum İçeriği

1. **SonarQube Nedir?** - Statik kod analizi ve güvenlik taraması
2. **Kurulum** - Docker ve Kubernetes seçenekleri
3. **Canlı Demo** - Örnek kodların analizi
4. **Güvenlik Bulguları** - Critical, Major, Minor kategorileri
5. **CI/CD Entegrasyonu** - GitHub Actions örneği

## 📝 Notlar

- Lokal kurulum demo/test içindir
- Production için Kubernetes kurulumunu kullanın
- Demo kodlar **kasıtlı hatalar** içerir, production'da kullanmayın

## 📄 Lisans

MIT
