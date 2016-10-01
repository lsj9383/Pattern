using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InnerDrive.Machine
{
    class StateOne : State
    {
        StateMachine Machine = null;

        public StateOne(StateMachine machine)
        {
            Machine = machine;
        }

        public void Next()
        {
            ;
        }
    }

    class StateTwo : State
    {
        StateMachine Machine = null;

        public StateTwo(StateMachine machine)
        {
            Machine = machine;
        }

        public void Next()
        {
            ;
        }
    }
}
