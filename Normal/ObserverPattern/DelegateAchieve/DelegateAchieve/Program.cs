using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DelegateAchieve
{
    class Observer1
    {
        private string name;
        public Observer1(string name)
        {
            this.name = name;
        }

        public void Caller(object o, object e)
        {
            var eve = e as ButtonEventArgs;
            if (eve != null)
            {
                Console.WriteLine(name + ": " + eve.ActionInform);
            }
            else
            {
                Console.WriteLine("Error");
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Button btn = new Button();

            btn.obsInterface = new Observer1("observer1").Caller;
            btn.obsInterface += (o, e) =>
                {
                    var eve = e as ButtonEventArgs;
                    if (eve != null)
                    {
                        Console.WriteLine("delegate : " + eve.ActionInform);
                    }
                    else
                    {
                        Console.WriteLine("Error");
                    }
                };
            Console.WriteLine("Button will press...");
            btn.PressEventTarget();

            Console.WriteLine("\nButton will realse...");
            btn.ReleaseEventTarget();

            Console.ReadLine();
        }
    }
}
