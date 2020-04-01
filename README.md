# Payments and Contacts using the mock Api from PicPay
- Language used: Kotlin
- CI with Gitlab for lint and unit testing
- Unit testing using JUnit + Mockk
- (Some of the) libraries used: Retrofit, Dagger2, RxJava, Realm

It is a simulation for money transfer to another user using credit card.

The user selects a contact from a list, types the value to be sent and finishes the payment using the current credit card registered. If there is no credit card registered, he will need to register one (giving the number, expiration date and CVV) before finishing the payment.

The credit card should be persisted in the application for future payments.

The layout for reference: Android - https://goo.gl/M5RFzY

<img src="https://i.imgur.com/kTszvWB.pngg" width="480"/>
<img src="https://i.imgur.com/qar8Sss.png" width="480"/>
<img src="https://i.imgur.com/q99XyCF.png" width="480"/>
<img src="https://i.imgur.com/SXLfIpZ.png" width="480"/>
