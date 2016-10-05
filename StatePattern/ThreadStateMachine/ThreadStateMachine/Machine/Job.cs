using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Manager;

namespace ThreadStateMachine.Machine
{
    class JobResult
    {
        int isSuccess = 0;
        private Object lockRoot = new Object();

        public void SetStatus(int success)
        {
            lock (lockRoot)
            {
                isSuccess = success;
            }
        }

        public int GetStatus()
        {
            lock (lockRoot)
            {
                int tmp = isSuccess;
                isSuccess = 0;          //isSuccess读取一次后，直接置零。
                return tmp;
            }
        }
    }

    abstract class Job
    {
        public abstract void Process(StateMachine Machine);
    }
}
