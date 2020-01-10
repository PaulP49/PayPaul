PayPaul
================================

Setup
--------------------------

Standardmäßig benutzt dieses Programm eine MySQL 5.7 Datenbank.  
In `application.properties` kann die Datenbankverbindung eingestellt werden.

Nach dem Start des Programms ist PayPaul unter `localhost:9090` zu erreichen.
  
Transaction API
--------------------------
Um eine neue Transaktion anzulegen:
  
`POST localhost:9090/requestTransaction`  

Authorisierung: Basic Auth header  
`Basic <email:password(base64 encoded)>`

Die benötigten Daten bitte als JSON im body angeben (TransactionDTO):  
```json
{
 "receiver": "dev@dev.de",
 "amount": 100,
 "paymentReference": "test"
}
```  
<br/>

Um Transaktionsbestätigungen zu erhalten, muss eine Payment Notification angelegt werden.
Bei Eintritt der ausgewählten Events wird an die angegebene URL ein POST-Request geschickt mit Daten von der Form (CompletedTransactionDTO):

```json
{
  "id" : 17,
  "sender" : "null@dev.de",
  "receiver" : "dev@dev.de",
  "paymentReference" : "test",
  "amount" : 100
}
```

<br/>

In `paypaul-dtos.jar` befinden sich die beiden verwendeten DTO-Klassen.

  

