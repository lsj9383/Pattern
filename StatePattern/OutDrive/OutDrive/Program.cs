using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using OutDrive.Machine;

namespace OutDrive
{
    class Program
    {
        static void Main(string[] args)
        {
            StateMachine machine = new StateMachine();
            machine.Init(2);
            machine.InsertQuarter();
            machine.TurnCrank();
            machine.TurnCrank();
            machine.Dispose();

            machine.InsertQuarter();
            machine.TurnCrank();
            machine.Dispose();

            machine.InsertQuarter();
            machine.TurnCrank();
            machine.Dispose();

            Console.ReadLine();
        }
    }
}
