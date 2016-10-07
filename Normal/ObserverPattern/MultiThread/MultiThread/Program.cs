using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MultiThread
{
    class Program
    {
        static void Main(string[] args)
        {
            Subject subject = new Subject();

            subject.obs += (o, e) =>
            {
                Console.WriteLine("Observe1 : Process Finish");
            };

            subject.obs += (o, e) =>
            {
                Console.WriteLine("Observe2 : Process Finish");
            };

            subject.AsyncProcess();

            Console.WriteLine("Main Done!");
            Console.ReadLine();
        }
    }
}
