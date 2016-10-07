using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace MultiThread
{
    class Invoker
    {
        private Action Command;

        public void SetCommand(Action Command)
        {
            this.Command = Command;
        }

        public void Action()
        {
            new Thread(() =>
            {
                Command();
            }).Start();
        }
    }
}
