using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterfaceAchieve
{
    interface Observer
    {
        void Caller(object o, object e);
    }
}
