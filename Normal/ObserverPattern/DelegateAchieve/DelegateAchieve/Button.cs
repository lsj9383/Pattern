using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DelegateAchieve
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
        public Action<Object, Object> obsInterface;        //来自观察者提供的接口

        //Event
        public void PressEventTarget()         //按下事件触发
        {
            obsInterface(this, new ButtonEventArgs("PRESS"));
        }

        public void ReleaseEventTarget()         //松开事件触发
        {
            obsInterface(this, new ButtonEventArgs("RELEASE"));
            ClickEventTarget();
        }

        private void ClickEventTarget()         //单击事件触发
        {
            obsInterface(this, new ButtonEventArgs("CLICK"));
        }
    }
}
