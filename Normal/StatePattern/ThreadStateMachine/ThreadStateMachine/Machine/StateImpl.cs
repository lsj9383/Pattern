using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

using Manager;

namespace ThreadStateMachine.Machine
{
    //繁忙状态
    class Busy : State
    {
        public Busy(StateMachine machine):base(machine){ }

        public override string ToString()
        {
            return "BUSY";
        }

        public override void Initial(EventHandler<EventArgs> eHandler)
        {
            throw new NotImplementedException();                //throw.
        }

        public override void Next()
        {
            int result = sMachine.jobResult.GetStatus();       //获得结果，在获得操作结果后，会立刻将内部结果置为0
            if (result == 1)
            {
                sMachine.SetState(sMachine.LOOP);               //恢复轮询job状态
            }
            else if (result == -1)
            {
                sMachine.SetState(sMachine.ERROR);              //进入错误状态，将会进行错误处理
            }
            else if (result == -2)                              //该状态机初始化未完成，不可用
            {
                sMachine.SetState(sMachine.EMPTY);
            }
            else
            {
                ;       //still BUSY, No action
            }
        }
    }

    //轮询状态
    class Looping : State
    {
        public Looping(StateMachine machine) : base(machine) { }

        public override string ToString()
        {
            return "LOOP";
        }

        public override void Initial(EventHandler<EventArgs> eHandler)
        {
            throw new NotImplementedException();                //throw.
        }

        public override void Next()
        {
            if (sMachine.JobsCount() > 0)
            {
                sMachine.SetState(sMachine.BUSY);               //设为忙状态
                sMachine.DequeueJobs().Process(sMachine);       //对工作出队，并进行处理
            }
        }
    }

    //错误状态
    class Error : State
    {
        private EventHandler<EventArgs> errorHandler;
        public Error(StateMachine machine, EventHandler<EventArgs> handler) : base(machine) { errorHandler = handler; }

        public override string ToString()
        {
            return "ERROR";
        }

        public override void Initial(EventHandler<EventArgs> eHandler)
        {
            throw new NotImplementedException();                //throw.
        }

        public override void Next()
        {
            errorHandler(sMachine, new EventArgs());            //错误处理
            sMachine.SetState(sMachine.LOOP);                   //恢复轮询状态            
        }
    }

    //起始状态
    class Empty : State
    {
        public Empty(StateMachine machine) : base(machine) { }

        public override string ToString()
        {
            return "EMPTY";
        }

        public override void Initial(EventHandler<EventArgs> eHandler)
        {
            sMachine.SetState(sMachine.BUSY);       //初始化线程打开前，先将状态置为繁忙.

            Task<bool> task = new Task<bool>(InitialProcess);
            task.ContinueWith(t =>
                {
                    eHandler(sMachine, new EventArgs());
                    if (t.Result == false)
                    {   //初始化失败，这个状态机不可用, 返回错误代码
                        sMachine.jobResult.SetStatus(-2);
                    }
                    else
                    {   //初始化成功
                        sMachine.jobResult.SetStatus(1);
                    }
                });

            task.Start();
        }

        public override void Next()
        {
            throw new NotImplementedException();
        }

        private bool InitialProcess()
        {
            Thread.Sleep(5000);
            return true;
        }
    }
}
