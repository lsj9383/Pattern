using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Manager;

namespace ThreadStateMachine.Machine
{
    class UploadJob : Job
    {
        string TargetFile;
        string LocalFile;
        int Life;

        public UploadJob(string tf, string lf)
        {
            TargetFile = tf;
            LocalFile = lf;
            Life = 3;           //3次机会
        }

        public UploadJob(string tf, string lf, int life)
        {
            TargetFile = tf;
            LocalFile = lf;
            Life = life;           //3次提交
        }

        public void Process(StateMachine Machine)
        {
            Machine.SetState(Machine.BUSY);             //在开启线程前，必须确保状态已经是BUSY

            Machine.itemManger.SucceededHandler = (sender, e) => 
            {
                Machine.jobResult.SetStatus(1);         //成功
            };
            Machine.itemManger.FailedHandler = (sender, e) =>
            {
                if (Life > 0)
                {
                    Machine.EnqueueJobs(new UploadJob(TargetFile, LocalFile, Life - 1));              //还有生命，就将Job入队，以重新操作
                    Machine.jobResult.SetStatus(1);         //因为还有生命，所以认为该次操作成功
                }
                else 
                {
                    Machine.jobResult.SetStatus(-1);        //失败, 会进行错误状态处理的。
                }
            };

            Machine.itemManger.Upload(TargetFile, LocalFile);
        }
    }
}
