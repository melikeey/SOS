# SOS
This utility library


## SDK kullanımı 

Bu kütüphane sizin kod yazarken ihtiyacınız olan tüm utility classları toplamayı hedeflemektedir

## Kullanım 

Kullanım oldukça basittir. app seviyesindeki build.gradle'a modülümüzü bağladıktan sonra SOS static class'ı yardımıyla 
utility'leri kullanabileceksiniz : 

Örnek kullanım : 

     if (SoS.isNetworkConnected(getApplicationContext())){
                    SoS.showToast(getApplicationContext(), "You connected !");
                }else {
                    SoS.logError("Error Connection");
                }



Herhangi bir sıkıntıda iletişime geçmekten çekinmeyiniz. 
