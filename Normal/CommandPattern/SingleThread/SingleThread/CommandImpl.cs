using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SingleThread
{
    class Command1 : Command
    {
        public ReceiverA receiver;

        public Command1(ReceiverA receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            Console.WriteLine(receiver.Name);
        }
    }

    class Command2 : Command
    {
        public ReceiverB receiver;

        public Command2(ReceiverB receiver)
        {
            this.receiver = receiver;
        }

        public void Execute()
        {
            Console.WriteLine(receiver.Name);
        }
    }
}
