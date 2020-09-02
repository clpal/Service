
# What can be done with the IntentService vs Service by focuses on:

 First) When to use them?

Service:


The Service can be used in tasks with no UI, but shouldn't be too long. If you need to perform long tasks, you must use threads within Service. 

Service is a base class of service implementation. Service class is run in the application’s main thread which may reduce the application performance. 

 IntentService :

The IntentService can be used in long tasks usually with no communication to Main Thread. If communication is required, can use Main Thread handler or broadcast intents.

Thus, IntentService, which is a direct subclass of Service is borned to make things easier. The IntentService is used to perform a certain task in the background. Once done, the instance of IntentService terminate itself automatically. Examples for its usage would be to download a certain resources from the Internet.


Intent service is a type of service that does a few specific things very well.

1. Thread management(Creation of worker thread): It automatically processes all incoming requests in a separate thread taking the burden of thread management away from the developer.

2. Request Queue: It queues up all the incoming requests and they are processed one by one

3. Stop point: Once the request Queue is empty it automatically stops itself, so the developer need not worry about handling the service lifecycle.
4. IntentService is useful for “fire and forget” operations,taking care of background Thread creation and cleanup.

As a developer, we just have to focus on our core business logic and implement it in the OnHandleIntent callback method.



Second) How to trigger them?

1-The Service can be triggered by calling method startService().

2-The IntentService can be triggered by using an Intent, it spawns a new worker thread and the method onHandleIntent() is called on this thread.


Third) When to stop them?

1-If you implement a Service, it is your responsibility to stop the service when its work is done, by calling stopSelf() or stopService ().

2-The IntentService stops the service, after all, start requests have been handled, so you never have to call stopSelf().

Fourth) what behaves with MainThread and worker thread ?

Services run in the main thread of the application. Owing to this, when we use just a Service to execute a long running operation ( >5s), the app crashes and displays the ANR dialog. To avoid this, we typically spawn a new thread from the service and off load the long running operation in it.

Now, to cater to this implicitly, IntentService is used. IntentService automatically spawns a thread and executes the required operation without blocking the main thread.

Differences

1:  Service class uses the application's main thread, while IntentService creates a worker thread and uses that thread to run the service.<br/>

2: IntentService creates a queue that passes one intent at a time to onHandleIntent(). Thus, implementing a multi-thread should be made by extending Service class directly. Service class needs a manual stop using stopSelf(). Meanwhile, IntentService automatically stops itself when it finishes execution.<br/>
3:   IntentService implements onBind() that returns null. This means that the IntentService can not be bound by default.<br/>
4: IntentService implements onStartCommand() that sends Intent to queue and to onHandleIntent().<br/>
