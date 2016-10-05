using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OutDrive.Machine
{
    class StateMachine
    {
        public State mNoQuarterState  { get; private set; }
        public State mHasQuarterState { get; private set; }
        public State mSoldOutState    { get; private set; }
        public State mSoldState       { get; private set; }

        //状态机内部属性
        private State mState;
        public int count { get; private set; }

        public StateMachine()
        {
            mSoldOutState = new SoldOutState(this);
            mSoldState = new SoldState(this);
            mNoQuarterState = new NoQuarterState(this);
            mHasQuarterState = new HasQuarterState(this);
        }

        public void Init(int numberGumballs)
        {
            count = numberGumballs;
            if (count > 0)
            {
                mState = mNoQuarterState;
            }
            else
            {
                mState = mSoldOutState;
            }
        }

        public void SetState(State sta)
        {
            mState = sta;
        }

        public void releaseBall()
        {
            Console.WriteLine("A gum comes rolling out the slot...");
            if (count != 0)
            {
                count = count - 1;
            }
        }

        //不同的状态机操作，直接让当前状态作对应的操作。
        public void InsertQuarter()
        {
            mState.InsertQuarter();
        }

        public void EjectQuarter()
        {
            mState.EjectQuarter();
        }

        public void TurnCrank()
        {
            mState.TurnCrank();
        }

        public void Dispose()
        {
            mState.Dispose();
        }
    }
}
