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
            for (int cntLoop=0; true; cntLoop++ )
            {
                Thread.Sleep(1000);
                Console.WriteLine("Main Thread Looping, Job has : " + sm.JobsCount());
                if (cntLoop < 10)
                {
                    sm.EnqueueJobs(new UploadJob("", ""));
                }
            }
        }

        static void Main(string[] args)
        {
            sm = new StateMachine((sender, e)=>{});
            sm.Initial((sender, e) => 
            {
                Console.WriteLine("Initial Finished");
            });
            sm.Start();

            MainThread();
        }
    }
}
