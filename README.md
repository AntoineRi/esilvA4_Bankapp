# esilvA4_Bankapp  Antoine Riouallon

1) Explain how you make sure the user is the right one to start the application.

In order to correctly authenticate the user of the application, I used password authentication. I wanted to use biometric fingerprint authentication but having an Apple phone I couldn't try it with my phone directly, I could only use the emulator.

2) How do you securely enroll user data on your phone?

To enroll user data on the phone, I used the internal storage of the phone directly.  So I use a private data storage place of the application, these data are stored and used for their own application. Other applications can't access it, so it's a secure method to save the data.

3) How did you hide the URL of the API?

The URL of the API is hidden in the gradle.properties and we access the key using buildconfig. I encoded it and stored it in the gradle so that it doesn't appear as plain text in my code, so the API URL is well secured in my code.

How works my application : 
So first we arrive on the login page, we have to enter our username and password.

![image](https://user-images.githubusercontent.com/80207183/110250573-47128380-7f7c-11eb-9628-7ef474e44d4a.png)

 If we made a mistake we have the message "Oh, I think you made a mistake in your username or password", but in my app the toasts doesn't want to display well like we can see : 

![image](https://user-images.githubusercontent.com/80207183/110250801-ad4bd600-7f7d-11eb-8b3b-6952a6a10605.png)

Also, if we make 3 mistakes we directly close the app. 

![image](https://user-images.githubusercontent.com/80207183/110251031-d751c800-7f7e-11eb-9269-7106fe392642.png)

After, when we log well, we are going directly to the next page which we can see all the accounts of the API, with 3 buttons: 

![image](https://user-images.githubusercontent.com/80207183/110251113-4af3d500-7f7f-11eb-8b28-0df46e28411c.png)

A refresh button, which refresh the all page, a save button which save the datas for those who wants to use the app without connection and a load button that load the datas.

![image](https://user-images.githubusercontent.com/80207183/110251203-cce3fe00-7f7f-11eb-97c4-374da6727f40.png)

"File Loaded"

![image](https://user-images.githubusercontent.com/80207183/110251224-e7b67280-7f7f-11eb-850a-3468dd5be756.png)

"File saved"

And as for the offline part, we have first to connect with internet to do the connection with the API and after we can open the app without connection and see the datas.

! [image] (https://user-images.githubusercontent.com/80207183/110251368-6f03e600-7f80-11eb-9f8b-6527b251d7c8.png)
![image](https://user-images.githubusercontent.com/80207183/110251791-71ffd600-7f82-11eb-9e50-b8dcca02f9ff.png)

