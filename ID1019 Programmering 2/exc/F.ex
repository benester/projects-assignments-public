defmodule F do
def flatten([])do [] end
def flatten([h|t])do
    flatten(h) ++ flatten(t)
end
    def flatten(single)do [single] end

    def pmap(list, fnc)do
        pid = self()
        Enum.map(list, fn(x) -> spawn(fn()->send(pid, fnc.(x))end)end)
        Enum.map(list, fn(_) -> receive do x -> x end end)
    end
end