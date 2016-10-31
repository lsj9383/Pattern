using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Singleton
{
    class Unique
    {
        private static Unique Instance = null;
        private static object root = new object();

        private Unique() { }

        public static Unique GetInstance() 
        {
            if (Instance == null)
            {
                lock (root)
                {
                    if (Instance == null)
                    {
                        Instance = new Unique();
                    }
                }
            }

            return Instance;
        }
    }
}
