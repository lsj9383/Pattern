using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Threading;

using ThreadStateMachine.Machine;

namespace ThreadStateMachine
{
    class Program
    {
        static StateMachine sm = null;

        static void MainThread()
        {
            while (true)
            {
                Thread.Sleep(1000);
                Console.WriteLine("Main Thread Looping");
            }
        }

        static void Main(string[] args)
        {
            sm = new StateMachine((sender, e)=>{});
            sm.Initial((sender, e) => 
            {
                Console.WriteLine("Initial Finished");
            });

            MainThread();
        }
    }
}
