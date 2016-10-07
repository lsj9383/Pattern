using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace MultiThread
{
    class Program
    {
        static void Main(string[] args)
        {
            Invoker invoker = new Invoker();

            invoker.SetCommand(() => 
            {
                for (int i = 0; i < 10; i++)
                {
                    Thread.Sleep(100);
                    Console.WriteLine(i);
                }
            });

            invoker.Action();
            Console.WriteLine("Main Done!");
        }
    }
}
