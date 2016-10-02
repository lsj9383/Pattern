using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OutDrive.Machine
{
    interface State
    {
        void InsertQuarter();    //插入金钱
        void EjectQuarter();     //退回金钱
        void TurnCrank();        //扭转滑杆
        void Dispose();          //发放糖果
    }

    //出售状态
    class SoldState : State
    {
        private StateMachine sMachine;

        public SoldState(StateMachine Machine)
        {
            sMachine = Machine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("please wait, we're already giving you a gumball");
        }

        public void EjectQuarter()
        {
            Console.WriteLine("Sorry, you have tunred the crank");
        }

        public void TurnCrank()
        {
            Console.WriteLine("Turnning twice doesn't get you another gumball");
        }

        public void Dispose()
        {
            sMachine.releaseBall();
            if (sMachine.count > 0)
            {
                sMachine.SetState(sMachine.mNoQuarterState);
            }
            else
            {
                Console.WriteLine("Oops, out of gumballs!");
                sMachine.SetState(sMachine.mSoldOutState);
            }
        }

        public override string ToString()
        {
            return "SoldState";
        }
    }

    //售空状态
    class SoldOutState : State
    {
        private StateMachine sMachine;

        public SoldOutState(StateMachine Machine)
        {
            sMachine = Machine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("MACHINE STOP");
        }

        public void EjectQuarter()
        {
            Console.WriteLine("MACHINE STOP");
        }

        public void TurnCrank()
        {
            Console.WriteLine("MACHINE STOP");
        }

        public void Dispose()
        {
            Console.WriteLine("MACHINE STOP");
        }

        public override string ToString()
        {
            return "SoldOutState";
        }
    }

    //无钱状态
    class NoQuarterState : State
    {
        private StateMachine sMachine;

        public NoQuarterState(StateMachine Machine)
        {
            sMachine = Machine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("You have inserted a quarter");
            sMachine.SetState(sMachine.mHasQuarterState);       //转到有钱状态
        }

        public void EjectQuarter()
        {
            Console.WriteLine("You haven't inserted a quarter");
        }

        public void TurnCrank()
        {
            Console.WriteLine("You turned, but there's no quarter");
        }

        public void Dispose()
        {
            Console.WriteLine("You need to pay first!");
        }

        public override string ToString()
        {
            return "NoQuarterState";
        }
    }

    class HasQuarterState : State
    {
        private StateMachine sMachine;

        public HasQuarterState(StateMachine Machine)
        {
            sMachine = Machine;
        }

        public void InsertQuarter()
        {
            Console.WriteLine("You can't insert another quarter");
        }

        public void EjectQuarter()
        {
            Console.WriteLine("Quarter returned");
            sMachine.SetState(sMachine.mNoQuarterState);    //转到无钱状态
        }

        public void TurnCrank()
        {
            Console.WriteLine("You turned ..");
            sMachine.SetState(sMachine.mSoldState);      //转到出售状态
        }

        public void Dispose()
        {
            Console.WriteLine("No gumball disposed");
        }

        public override string ToString()
        {
            return "HasQuarterState";
        }
    }
}
