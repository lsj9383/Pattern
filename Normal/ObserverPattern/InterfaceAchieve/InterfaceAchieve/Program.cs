using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterfaceAchieve
{

    class Observer1 : Observer
    {
        public void Caller(object o, object e)
        {
            var eve = e as ButtonEventArgs;
            if (eve != null)
            {
                Console.WriteLine("Observer1: " + eve.ActionInform);
            }
            else
            {
                Console.WriteLine("Error");
            }
        }
    }

    class Observer2 : Observer
    {
        public void Caller(object o, object e)
        {
            var eve = e as ButtonEventArgs;
            if (eve != null)
            {
                Console.WriteLine("Observer2: " + eve.ActionInform);
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

            btn.AddOberver(new Observer1());
            btn.AddOberver(new Observer2());

            Console.WriteLine("Button will press. ");
            btn.PressEventTarget();

            Console.WriteLine("\nButton will Relase. ");
            btn.ReleaseEventTarget();

            Console.ReadLine();
        }
    }
}
