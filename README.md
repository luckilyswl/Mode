# Mode
mvc、mvp、mvvm三种框架


1 MVC

    MVC，(Model View Controller)，是软件架构中最常见的一种框架，简单来说就是通过controller的控制去操作model层的数据，并且返回
给view层展示，具体见下图


    当用户发触事件的时候，view层会发送指令到controller层，接着controller去通知model层更新数据，model层更新完数据以后直接显示
在view层上，这就是MVC的工作原理。

Android中的MVC：

    对于原生的Android项目来说，layout.xml里面的xml文件就对应于MVC的view层，里面都是一些view的布局代码，而各种java bean，还有
一些类似repository类就对应于model层，至于controller层嘛，当然就是各种activity咯。比如你的界面有一个按钮，按下这个按钮去网
络上下载一个文件，这个按钮是view层的，是使用xml来写的，而那些和网络连接相关的代码写在其他类里，比如你可以写一个专门的
networkHelper类，这个就是model层，那怎么连接这两层呢？是通过 button.setOnClickListener()这个函数，这个函数就写在了
activity中，对应于controller层。是不是很清晰。

存在的问题：

    问题就在于xml作为view层，控制能力实在太弱了，你想去动态的改变一个页面的背景，或者动态的隐藏/显示一个按钮，这些都没办
    法在xml中做，只能把代码写在activity中，造成了activity既是controller层，又是view层的这样一个窘境。大家回想一下自己写
    的代码，如果是一个逻辑很复杂的页面，activity或者fragment是不是动辄上千行呢？这样不仅写起来麻烦，维护起来更是噩梦。
    view层和model层是相互可知的，这意味着两层之间存在耦合，耦合对于一个大型程序来说是非常致命的，因为这表示开发，测试，
    维护都需要花大量的精力。

正因为MVC存在上述的问题，所以引入后面要介绍的两个框架-MVP和MVVM。



2 MVP

     MVP，(Model View Presenter)作为MVC的演化，解决了MVC不少的缺点，对于Android来说，MVP的model层相对于MVC是一样的，而activity
和fragment不再是controller层，而是纯粹的view层，所有关于用户事件的转发全部交由presenter层处理。下面还是让我们看图

    从图中就可以看出，最明显的差别就是view层和model层不再相互可知，完全的解耦，取而代之的presenter层充当了桥梁的作用，用于操
作view层发出的事件传递到presenter层中，presenter层去操作model层，并且将数据返回给view层，整个过程中view层和model层完全没
有联系。看到这里大家可能会问，虽然view层和model层解耦了，但是view层和presenter层不是耦合在一起了吗？其实不是的，对于view
层和presenter层的通信，我们是可以通过接口实现的;具体的意思就是说我们的activity，fragment可以去实现实现定义好的接口，而在
对应的presenter中通过接口调用方法。不仅如此，我们还可以编写测试用的View，模拟用户的各种操作，从而实现对Presenter的测试。
这就解决了MVC模式中测试，维护难的问题。

注意：其实最好的方式是使用fragment作为view层，而activity则是用于创建view层(fragment)和presenter层(presenter)的一个控制器。
 ———————————————— 


原文链接：https://blog.csdn.net/chaoshenzhaoxichao/article/details/79871145