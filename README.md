
![alt text](https://glob.zoznam.sk/wp-content/uploads/2019/03/drive-44378_1280-1170x780.jpg)



# SOS
Bu yardımcı bir kütüphanedir.


## SDK kullanımı 

Bu kütüphane sizin kod yazarken ihtiyacınız olan tüm yardımcı methodları toplamayı hedeflemektedir

## Kurulum

Kurulum için modülu bilgisayarınıza indirdikten sonra projenizde modülü import ederek kullanmaya başlayabilirsiniz.

## Kullanım 

Kullanım oldukça basittir. app seviyesindeki build.gradle'a modülümüzü bağladıktan sonra SOS static class'ı yardımıyla 
utility'leri kullanabileceksiniz : 

Modülü bağlamak için build.gradle'a aşağıdaki kodu ekleyiniz; 
     
     implementation project(':sos')


Örnek kullanım : 

     if (SoS.isNetworkConnected(getApplicationContext())){
                    SoS.showToast(getApplicationContext(), "You connected !");
                }else {
                    SoS.logError("Error Connection");
                }



Herhangi bir problemde lütfen issue açmaktan çekinmeyiniz. 
