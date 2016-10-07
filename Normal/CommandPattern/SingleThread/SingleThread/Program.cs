using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SingleThread
{
    class CommandUser : Command
    {
        public void Execute()
        {
            Console.WriteLine("CommandUser");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Invoker invoker = new Invoker();
            invoker.SetCommand(new Command1(new ReceiverA()));
            invoker.Action();
            invoker.SetCommand(new Command2(new ReceiverB()));
            invoker.Action();
            invoker.SetCommand(new CommandUser());
            invoker.Action();

            Console.WriteLine("Done!");
            Console.ReadLine();
        }
    }
}
