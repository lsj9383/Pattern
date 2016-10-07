using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterfaceAchieve
{
    class ButtonEventArgs
    {
        public string ActionInform { get; private set; }

        public ButtonEventArgs(string inform)
        {
            ActionInform = inform;
        }
    }

    class Button
    {
        List<Observer> observers = new List<Observer>();       //观察者们

        //添加观察者
        public void AddOberver(Observer obse)
        {
            observers.Add(obse);
        }

        //Event
        public void PressEventTarget()         //按下事件触发
        {
            foreach (Observer obs in observers)
            {
                obs.Caller(this, new ButtonEventArgs("PRESS"));
            }
        }

        public void ReleaseEventTarget()         //松开事件触发
        {
            foreach (Observer obs in observers)
            {
                obs.Caller(this, new ButtonEventArgs("RELEASE"));
            }
            ClickEventTarget();         //既然有松开，那之前肯定有按下。按下+松开，完成了一次单击，因此触发单击事件。
        }

        private void ClickEventTarget()         //单击事件触发
        {
            foreach (Observer obs in observers)
            {
                obs.Caller(this, new ButtonEventArgs("CLICK"));
            }
        }
    }
}
