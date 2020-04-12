defmodule Chopstick do
    def start do
        stick = spawn_link(fn -> init end)
    end
    def available() do
        receive do
            ... -> ...
            :quit -> :ok
        end
    end
    def gone() do
        receive do
            ... -> ...
            :quit -> :ok
        end
    end
    def init do

    end
end