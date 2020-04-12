defmodule Ackerman do

    def ackermanCalc(0,n) do
        n + 1
    end
    def ackermanCalc(m, 0) do
        cond do
            m > 0 ->
                ackermanCalc(m - 1, 1)
            true ->
                ackermanCalc(m-1, ackermanCalc(m, -1))
        end
    end
    def ackermanCalc(m,n) do
        ackermanCalc(m-1,ackermanCalc(m,n-1))
    end 

end