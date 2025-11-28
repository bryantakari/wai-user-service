Hi There, This is user service for authentication purpose.




generate key:
keytool -genkeypair -alias boot -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -storepass changeit -dname "CN=localhost"
