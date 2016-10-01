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
            if (Machine.JobsCount() > 0)
            {
                Machine.DequeueJob().Process();
            }
            Machine.SetState(Machine.stateTwo);
        }

        public override string ToString()
        {
            return "StateOne";
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
            Machine.SetState(Machine.stateOne);
        }

        public override string ToString()
        {
            return "StateTwo";
        }
    }
}
