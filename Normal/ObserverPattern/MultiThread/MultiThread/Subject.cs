using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace MultiThread
{
    class SubjectEventArgs
    { 
    
    }

    class Subject
    {
        public Action<object, object> obs;

        public void AsyncProcess()
        {
            Task task = new Task(() => 
            {
                Console.WriteLine("Start Thread");
                Thread.Sleep(2000);
                Console.WriteLine("End Thread");
            });

            task.ContinueWith((t) =>
            {
                obs(this, new SubjectEventArgs());
            });

            task.Start();
        }
    }
}
