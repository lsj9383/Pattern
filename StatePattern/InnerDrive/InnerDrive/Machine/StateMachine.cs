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

        public StateMachine()
        {
            stateOne = new StateOne(this);
            stateTwo = new StateOne(this);
            state = stateOne;
        }

        public void Start()
        {
            while (true)
            {
                state.Next();           //状态操作与更迭
                Thread.Sleep(10);
            }
        }
    }
}
