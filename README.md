PayPaul
================================

Dieses Programm wird gehostet auf <http://im-codd:8827/>.
  
Tutorial
--------------------------
Um PayPaul zu verwenden, muss zuerst ein Account angelegt werden.
Dazu füllen Sie bitte das Registrierungsformular, erreichbar über den Button bei dem Login oder über /register, aus. Nach dem Anlegen des Accounts können Sie sich einloggen.
<br/>
<br/>
Bevor Sie Ihren Account für Transaktionen nutzen können, müssen Sie zuerst eine Zahlungsmethode hinzufügen.
<br/>
Danach können Sie über diese Zahlungsmethode Guthaben aufladen.
<br/><br/>
Nach diesen Schritten ist Ihr Account bereit, um für Transaktionen verwendet zu werden. Wenn Sie über eingehende oder ausgehende Zahlungen mittels eines Webhooks benachrichtigt werden wollen,
 können Sie eine Payment Notification anlegen.
 <br/><br/>
 Auf PayPaul greifen folgende Partnerprojekte zu: <br/>
 Bazaar: <http://im-codd:8825/> <br/>
 Packlon: <http://im-codd:8820/>
 
 PayPaul verwendet Bazaar, um angefragte Zahlungen zu bestätigen. Über eine Payment Notification, festgelegt im PayPaul Account von Bazaar werden Benachrichtigungen für eingehende Zahlungen an das Partnersystem versandt.
 <br/><br/>
 Falls die Registrierung nicht funktioniert, können Sie folgenden Default-Account verwenden:
 <br/>
 Email: test@dev.de
<br/>
Passwort: test123
  
Transaction API
--------------------------
Um eine neue Transaktion über die API anzulegen:
  
`POST http://im-codd:8827/requestTransaction`  

Authorisierung: Basic Auth header  
`Basic <email:password(base64 encoded)>`

Die benötigten Daten vom Typ TransactionDTO:  
```json
{
 "receiver": "dev@dev.de",
 "amount": 100,
 "paymentReference": "test"
}
```  
<br/>

Um Transaktionsbestätigungen zu erhalten, muss eine Payment Notification angelegt werden.
Bei Eintritt der ausgewählten Events wird an die angegebene URL ein POST-Request geschickt mit Daten vom Typ CompletedTransactionDTO:

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

In `paypaul-dtos.jar` befinden sich die beiden verwendeten DTO-Klassen TransactionDTO und CompletedTransactionDTO.

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
Es folgen die überarbeiteten Modelle von Meilenstein 1.<br/>
<mark style="background-color: red">Gelöschte Elemente sind rot markiert. </mark><br/>
<mark style="background-color: limegreen">Neu hinzugefügte Elemente sind grün markiert. </mark>
  

