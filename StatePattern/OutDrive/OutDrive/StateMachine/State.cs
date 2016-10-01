using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OutDrive.StateMachine
{
    interface State
    {
        void InsertQuarter();    //插入金钱
        void EjectQuarter();     //退回金钱
        void TurnCrank();        //扭转滑杆
        void Dispose();          //发放糖果
    }

    //售出状态
    class SoldState : State
    {
        private StateMachine sMachine;

        public SoldState(StateMachine Machine)
        {
            sMachine = Machine;
        }

        public void InsertQuarter()
        {

        }

        public void EjectQuarter()
        {

        }

        public void TurnCrank()
        { 
        
        }

        public void Dispose()
        { 
        
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

        }

        public void EjectQuarter()
        {

        }

        public void TurnCrank()
        {

        }

        public void Dispose()
        {

        }
    }
}
