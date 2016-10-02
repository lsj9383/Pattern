using System.IO;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using System;
using System.Threading;
using System.Collections.Generic;

namespace Manager
{
    class ItemEventArgs : EventArgs
    {
        public bool isSuccess { get; private set; }
        public string Information { get; private set; }

        public ItemEventArgs(bool isSuccess)
        {
            this.isSuccess = isSuccess;
        }

        public ItemEventArgs(bool isSuccess, string Information)
        {
            this.isSuccess = isSuccess;
            this.Information = Information;
        }

    }

    delegate void AgentSucceededHandler(object sender, ItemEventArgs e);
    delegate void AgentFailedHandler(object sender, ItemEventArgs e);

    class ItemManager
    {
        public EventHandler<ItemEventArgs> SucceededHandler = (sender, e) => { };
        public EventHandler<ItemEventArgs> FailedHandler = (sender, e) => { };

        public ItemManager()
        {
            SucceededHandler = (sender, e) => { };
            FailedHandler = (sender, e) => { };
        }

        public void Initial()
        {
            Task task = Task.Run(() => { Thread.Sleep(2000); });
            task.ContinueWith(t => 
            {
                CallEventHanlder(new ItemEventArgs(!t.IsFaulted));
            });
        }

        public void Upload(string targetFile, string LocalFile)
        {

            Task<bool> task = Task.Run<bool>(() => { Thread.Sleep(2000); return true; });
            task.ContinueWith(t =>
            {
                CallEventHanlder(new ItemEventArgs(!t.IsFaulted));
            });
        }

        private void CallEventHanlder(ItemEventArgs eve)
        {
            if (eve.isSuccess)
            {   //成功
                SucceededHandler(this, eve);
            }
            else
            {   //失败
                FailedHandler(this, eve);
            }
        }
    }
}