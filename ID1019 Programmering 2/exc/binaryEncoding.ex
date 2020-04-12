defmodule Binary  do
    def to_binary(n)do to_binary(n,[])end
    
    def to_binary(n, [])do
        to_binary(div(n,2), [rem(n,2)])
    end
    def to_binary(n, b)do
        cond do
            n != 0 ->
                to_binary(div(n,2), [rem(n,2) | b])
            true ->
                b
        end
    end

    def to_integer(b)do to_integer(b, 0) end
    def to_integer([], val)do val end
    def to_integer([h|t], val)do
        cond do 
            h == 1 ->
                to_integer(t, val + :math.pow(2, length([h|t])-1))
            h == 0 ->
                to_integer(t, val)
        end
    end
end