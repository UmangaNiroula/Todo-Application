# Todo Application

This is an application where a user can add, delete, and update any task. The aim of this app is to learn about the fragments, room database,
and MVVM pattern. As a result three java classes that are VIEW, VIEWMODEL and MODLE were created for the MMVM pattern. Simiallary, for the fragmanet
section two layout resource are inflated. Finally the room database is used to store, update and delete the taks data.

# Design of Todo App


Here a simple design of the application is shown where in the bottom left corner a flaoting button is implemented


<img width="369" alt="1" src="https://user-images.githubusercontent.com/53705624/170883763-5c14aaba-81dd-4f6b-8797-a9224336d111.png">


While clicking the float button another resource file is inflated to the main activity that slides form the bottom of the screen



<img width="369" alt="2" src="https://user-images.githubusercontent.com/53705624/170883793-943941df-ec8b-453c-bbc7-1ba4edb3b0d5.png">


In the EditText section the user can write about their task and task description


<img width="369" alt="3" src="https://user-images.githubusercontent.com/53705624/170883804-099c1186-d548-4c42-8ec2-07bc4eb3bc4a.png">


After picking the date the ADD button will add the data to the databse which is then displayed in recycler view that is inflated with
another layout resource file


# Database

Here Room database is used as shown in the inspection below


<img width="1400" alt="Screen Shot 2022-05-29 at 12 15 46 AM" src="https://user-images.githubusercontent.com/50906104/170838440-8392565d-87d1-4147-b680-dd59b4c57e34.png">


# CURD Operations

Since the database is created the CURD operation becomes very easy as shown in the screenshot below the user can edit, update and delete the
task.

<img width="369" alt="3" src="https://user-images.githubusercontent.com/53705624/170883843-9f5140a1-0e93-4481-9aef-92690a470dee.png">

<img width="369" alt="4" src="https://user-images.githubusercontent.com/53705624/170883847-7b736efc-0646-4db8-9dfd-8ed7b769968d.png">

Deleting Task

<img width="369" alt="5" src="https://user-images.githubusercontent.com/53705624/170883854-37d48d85-0ec8-4dca-a8ff-12193c338b1a.png">


# Activity LifeCycle

 
  Multiple Lifecycle are used such as :
 
  onStart()
 
 
  onCreat()
 
 
  onPause()
 
 
  onStop()
 
 
  onDestroy()
 
 
# Fragments

Fragments are used here by inflating two resource files as shown in the scrrenshot below:


<img width="352" alt="Screen Shot 2022-05-29 at 11 22 09 PM" src="https://user-images.githubusercontent.com/53705624/170883894-6f59efbd-7a40-4161-86e6-99e2cb65d460.png">

<img width="411" alt="Screen Shot 2022-05-29 at 11 22 35 PM" src="https://user-images.githubusercontent.com/53705624/170883913-2454e6c9-00bb-4931-befb-d6f9137948ad.png">



# Recycler View

Recycler View has been used to show multiple cardviews that represents the tasks added

<img width="322" alt="Screen Shot 2022-05-29 at 11 23 17 PM" src="https://user-images.githubusercontent.com/53705624/170883948-4b9098d8-0c83-490b-9aa7-b7115e597398.png">

 
# Libraries

Room Database

def room_version = "2.4.0-alpha03"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
   
   
Lifecycle Components


implementation "androidx.lifecycle:lifecycle-viewmodel:$rootProject.lifecycleVersion"
    implementation "androidx.lifecycle:lifecycle-livedata:$rootProject.lifecycleVersion"
    implementation "androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycleVersion"



# MVVM Pattern

MVVM patter means an architectural pattern that facilitates the separation of the development of the graphical user interface. Here the
following figure shows how the MVVM pattern can be used.


![image](https://user-images.githubusercontent.com/50906104/170838568-98608a46-282c-4222-a8b8-3786d73c1916.png)
