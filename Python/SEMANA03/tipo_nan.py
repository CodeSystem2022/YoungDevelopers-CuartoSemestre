import math
from decimal import Decimal

# NaN (Not a Number)
a = float('NaN')
print(f'a:{a}')

#Módulo math
a = float ('nan')
print(f'Es de tipo NaN (not a Number)?: {math.isnan(a)}')

# Módulo decimal
a = Decimal('nan')
print(f'Es de tipo Nan (Not a Number)?: {math.isnan(a)}')

