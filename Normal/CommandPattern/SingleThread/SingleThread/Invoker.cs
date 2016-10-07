using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SingleThread
{
    class Invoker
    {
        private Command m_cCommand;

        public void SetCommand(Command cmd)
        {
            m_cCommand = cmd;
        }

        public void Action()
        {
            m_cCommand.Execute();
        }
    }
}
