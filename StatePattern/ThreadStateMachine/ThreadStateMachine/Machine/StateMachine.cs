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


        public State BUSY = null;
        public State LOOP = null;
        public State ERROR = null;

        private State state = null;
        private Queue<Job> Jobs = new Queue<Job>();
        public JobResult jobResult { get; private set; }

        public StateMachine()
        { 
            BUSY = new Busy(this);
            LOOP = new Looping(this);
            ERROR = new Error(this);
        }

        public void Start()
        {
            new Thread(() => 
            {
                while (true)
                {
                    state.Next();
                    Thread.Sleep(10);       //释放当前线程
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
