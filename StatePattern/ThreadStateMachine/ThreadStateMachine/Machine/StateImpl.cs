using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Manager;

namespace ThreadStateMachine.Machine
{
    //繁忙状态
    class Busy : State
    {
        public Busy(StateMachine machine):base(machine){ }

        public override void Next()
        {
            int result = sMachine.jobResult.GetStatus();       //获得结果，在获得操作结果后，会立刻将内部结果置为0
            if (result == 1)
            {
                sMachine.SetState(sMachine.LOOP);               //恢复轮询job状态
            }
            else if (result == -1)
            {
                ;
            }
            else
            {
                ;       //still BUSY, No action
            }
        }
    }

    class Looping : State
    {
        public Looping(StateMachine machine) : base(machine) { }

        public override void Next()
        {
            if (sMachine.JobsCount() > 0)
            {
                sMachine.SetState(sMachine.BUSY);               //设为忙状态
                sMachine.DequeueJobs().Process(sMachine);       //对工作出队，并进行处理
            }
        }
    }

    class Error : State
    {
        public Error(StateMachine machine) : base(machine) { }

        public override void Next()
        {
            //错误处理
            sMachine.SetState(sMachine.LOOP);                   //恢复轮询状态
        }
    }
}
