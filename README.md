# Kodluyoruz-SolmazBootcamp-Bitirme-Projesi
Kodluyoruz &amp; Solmaz Gümrük  Müşavirliği Bootcamp Bitirme Projesi

Proje Konusu: 
Online uçak ve otobüs bileti satışı yapılmak istenmektedir. Uygulamanın gereksinimleri 
aşağıdaki gibidir.

Gereksinimler :

• Kullanıcılar sisteme kayıt ve login olabilmelidir.

• Kullanıcı kayıt işleminden sonra mail gönderilmelidir.

• Kullanıcı şifresi istediğiniz bir hashing algoritmasıyla database kaydedilmelidir.

• Admin kullanıcı yeni sefer ekleyebilir, iptal edebilir, toplam bilet satışını, bu satıştan elde edilen toplam ücreti görebilir.

• Kullanıcılar şehir bilgisi, taşıt türü(uçak & otobüs) veya tarih bilgisi ile tüm seferleri 
arayabilmelidir.

• Bireysel kullanıcı aynı sefer için en fazla 5 bilet alabilir.

• Bireysel kullanıcı tek bir siparişte en fazla 2 erkek yolcu için bilet alabilir.

• Kurumsal kullanıcı aynı sefer için en fazla 20 bilet alabilir.

• Satın alma işlemi başarılı ise işlem tamamlanmalı ve asenkron olarak bilet detayları  kullanıcının telefona numarasına sms gönderilmeli.

• SMS, mail ve push Notification gönderme işlemleri için sadece Database kayıt etme  işlemi yapılması yeterlidir. Fakat bu işlemler tek bir Servis(uygulama) üzerinden
ve  polimorfik davranış ile yapılmalıdır. 

• Kullancılar aldığı biletleri görebilmelidir. 

Sistem Kabulleri :

Kullanıcılar bireysel ve kurumsal olabilir.
SMS, Mail ve Push Notification gönderim işlemleri Asenkron olmalıdır.
Uçak yolcu kapasitesi: 189
Otobüs yolcu kapasitesi: 45
Ödeme şekli sadece Kredi kartı ve Havale / EFT olabilir.
Ödeme Servisi işlemleri Senkron olmalıdır.

Kullanılan Teknolojiler; 
• Java 17
• JUnit
• RESTful
• Spring Boot
• Docker 
• PostgreSQL 
• RabbbitMQ 
• Postman

Sistem İşleyişi:
Sistem bütün çağrıların tek bir yerden yönlendirildiği api gateway üzerinden çalışmakta. Ayrıca derslerde ölçekleme amacıyla kullandığımız loadbalancer sayesinde sistemde yaşanan yoğunluklarda uygunbilet-service den bir kaç tane daha yaratarak servislerin hizmet alanını genişletmiştik.

İlk olarak kullanıcıdan kayıt bilgisi istenmektedir, bu sayede kullanıcı uçak veya otobüs bileti alabilmektedir ve aldıkları biletleri görebilirler. 
Kullanıcının şifresi kayıt sırasında özel bir hashing algoritması ile veritabanına kaydedilir.

Kullanıcılar sisteme kayıt olduktan sonra login olabilmektedirler. Login için kullanıcı email ve şifre bilgisini girer. Eğer bilgiler doğruysa kullanıcı login olur.

Kullanıcı sisteme kayıt olduktan sonra rabbitmq tetiklenir, rabbitmq kullanıcının bilgilerini "notification" servisine iletir. Notification servis ise kullanıcı bilgisini alıp bunu gerekli dönüştürmeleri yaparak Mail sınıfına çevirip veritabanına kaydeder.

Kayıt sırasında eğer bir kullanıcı "admin@gmail.com" maili ile kayıt olursa kullanıcının rolü Admin olarak güncellenir ve sistemde değişiklikler, işlemler yapma hakkına sahip olur.

Admin otobüs veya uçak seferleri ekleyebilir, bu seferleri silebilir, bu seferlerden elde edilen bilet sayısını ve gelirini görüntüleyebilir.

Admin sefer oluşturma isteği attıktan sonra arka planda uçak ve otobüslerin koltuk dizilimleri ayarlanır ve ekrana sefer bilgisi ile koltuk bilgisi de döndürülür.
Uçakta 189, Otobüste ise 45 koltuk vardır. Koltuk tipleri ise tekli veya çiftlidir. Şuan koltuk tipleri tekli olarak ayarlanmıştır fakat bu da yapılacak küçük bir değişiklik ile tekli ve çiftli koltuklar olarak güncellenebilir.
Koltuklardaki müşterilerin isim, soyisim, cinsiyet, tc kimlik numaraları veritabanında görüntülenebilmektedir. Kullanıcılara ise sadece koltuğun dolu veya boş olduğu bilgisi döndürülür.

Kullanıcılar seferleri bulundukları şehirden gidecekleri şehre göre arayabilmektedir.
Kullanıcılar seferleri sefer gününe göre arayabilmektedir.
Kullanıcılar seferleri otobüs veya uçak olarak arayabilmektedir.
Kullanıcılar seferleri gidecekleri şehirden gidecekleri şehre, sefer gününe ve sefer türü(uçak, otobüs) olarak arayabilmektedirler.

Kullanıcılar sisteme kayıt olurken bireysel veya kurumsal olarak kayıt olabilmektedirler.
Bireysel kullanıcı aynı sefer için en fazla 5 bilet alabilmektedir.
Bireysel kullanıcı aynı sefer için tek bir siparişte en fazla 2 erkek yolcu için bilet alabilmektedir.
Kurumsal kullanıcı ise aynı sefer için en fazla 20 bilet alabilmektedir.

Kullanıcı bir sefer için bilet almak istediğinde öncelikle ödeme tipini seçmelidir. Ödeme tipi ise 2 tanedir: Birincisi kredi kartı, ikincisi ise havale yöntemidir.
Kullanıcı ödeme tipini ve ödeme bilgilerini girdikten sorna istediği boş koltukların numarasını, yolcunun ismi, soyismi, cinsiyeti, tc kimlik numarasını girmelidir.
Bu bilgileri girip ödeme isteğini attıktan sonra ödeme servisi olarak hizmeti veren ve "8081" portunda çalışan "uygunbilet-payment" servisine ödeme isteği ulaşır.
Eğer ödeme işlemi başarılıysa kullanıcı ekranına aldığı bilet veya biletler geri döner. 

UygunBilet-payment servisi gelen ödeme isteğini alır ve eksiklik yoksa ödemeyi başarılı olarak kaydeder ve veritabanına kaydeder. 
Ödeme işlemi başarılı olduysa "payment" servisi rabbitmq tetikler ve bir kuyruk oluşur. Bu kuyrukta ödeme bilgisi ücreti ve bilet alan kişilerin bilgileri "notification" servisine gönderilir.
Notification service gelen bilgileri veritabanına "Sms" sınıfı olarak kaydeder. Buradaki amaç kullanıcılara bilet detaylarını sms yoluyla göndermektedir. 

Proje Değerlendirme Kriterleri:

Projede çalışan "eureka-service" "8761" portunda, "uygunbilet-gateway" "8090" portunda, "uygunbilet-service" random bir portta, "uygunbilet-payment" servisi "8081" portunda hizmet vermektedir.
Bu servislerin çalışma şekli "Microservice diagram" klasöründe verilmiştir. 
Bu servislerin çalışması için gerekli olan api "get/post/delete" isteklerini içeren postman collectionslar da "Postman Collections" klasöründe yer almaktadır.

"UygunBilet" servisi, "UygunBilet-Payment" servisi ve "UygunBilet-Notifcation" servisinde yer alan entitylerin ilişkisel tabloları "Er Diagrams" klasöründe yer almaktadır.

"UygunBilet" servisi, "UygunBilet-Payment" servislerinin Controller katmanlarında unit test yazılmıştır. Unit test coverage oranları %100' dür. Coverage resimleri ise "Controller Coverage" klasöründe yer almaktadır.
"UygunBilet" servisi ve "UygunBilet-Payment" servislerinin Service katmanlarında unit test yazılmamıştır.

Loglama ve Exception Handling mekanizmaları kurulmuştur. Servislerin loglama faaliyetleri ayrıca dosya olarak ana klasörlerindeki dizine kaydedilmektedir. 


