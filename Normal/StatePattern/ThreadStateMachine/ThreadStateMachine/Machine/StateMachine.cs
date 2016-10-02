using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

using Manager;

namespace ThreadStateMachine.Machine
{
    class StateMachine
    {
        //耗时操作的对象，状态机的所有操作均基于该对象，该对象提供的操作全都是异步操作。
        public ItemManager itemManger {get; private set;}

        //必需状态
        public State BUSY   = null;
        public State LOOP   = null;
        public State ERROR  = null;
        public State EMPTY  = null;

        private State state = null;
        
        //工具组件
        private Queue<Job> Jobs = new Queue<Job>();
        public JobResult jobResult { get; private set; }

        public StateMachine(EventHandler<EventArgs> eErrorHandler)
        { 
            BUSY = new Busy(this);
            LOOP = new Looping(this);
            ERROR = new Error(this, eErrorHandler);
            EMPTY = new Empty(this);
            jobResult = new JobResult();
            itemManger = new ItemManager();

            state = EMPTY;      //等待初始化状态
        }

        public void Initial(EventHandler<EventArgs> eHandler)
        {
            state.Initial(eHandler);
        }

        public void Start()
        {
            new Thread(() => 
            {
                State oldState = state;
                while (true)
                {
                    state.Next();
                    Thread.Sleep(100);       //释放当前线程
                    if (oldState != state)
                    {
                        oldState = state;
                        Console.WriteLine(state);
                    }
                }
            }).Start();
        }

        //工作队列操作
        public void EnqueueJobs(Job job)
        {
            lock (Jobs)
            {
                Jobs.Enqueue(job);
            }
        }

        public Job DequeueJobs()
        {
            lock (Jobs)
            {
                return Jobs.Dequeue();
            }
        }

        public int JobsCount()
        {
            lock (Jobs)
            {
                return Jobs.Count;
            }
        }

        //状态转移
        public void SetState(State sta)
        {
            state = sta;
        }
    }
}
