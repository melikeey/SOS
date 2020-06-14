
![alt text](https://im.haberturk.com/2020/03/11/ver1583915850/2609960_87f666e88ebe2aa3873b3db8ccf52c79.jpg)



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
