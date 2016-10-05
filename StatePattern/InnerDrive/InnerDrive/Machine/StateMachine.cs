using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace InnerDrive.Machine
{
    class StateMachine
    {
        public State stateOne = null;
        public State stateTwo = null;

        private State state = null;
        private Queue<Job> Jobs;

        public StateMachine()
        {
            stateOne = new StateOne(this);
            stateTwo = new StateTwo(this);
            Jobs = new Queue<Job>();
            state = stateOne;
        }

        public void EnqueueJob(Job job)
        {
            Jobs.Enqueue(job);
        }

        public Job DequeueJob()
        {
            return Jobs.Dequeue();
        }

        public int JobsCount()
        {
            return Jobs.Count;
        }

        public void SetState(State sta)
        {
            state = sta;
        }

        public void Start()
        {
            while (true)
            {
                Console.WriteLine(state);
                state.Next();           //状态操作与更迭
                Thread.Sleep(1000);
            }
        }
    }
}
