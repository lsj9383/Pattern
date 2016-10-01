using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ThreadStateMachine.Machine
{
    abstract class State
    {
        protected StateMachine sMachine;

        public State(StateMachine machine)
        {
            sMachine = machine;
        }

        public abstract void Next();
        public abstract void Initial(EventHandler<EventArgs> eHandler);
    }
}
