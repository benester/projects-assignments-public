defmodule Env do
    
    def new() do [] end
      
    def add(id, str, env) do
        [{id, str} | env]
    end
    
    def lookup(id, str)do 
        List.keyfind(str, id, 0)
    end

    def closure(free, env) do

    end

    def remove([], env) do env end
    def remove(_, [])do [] end 
    def remove([head | tail], env)do
        List.keydelete(remove(tail, env), head, 0) #Kvadratisk?
    end
end

defmodule Eager do
    def eval_expr({:atm, id}, _) do {:ok, id} end
    def eval_expr({:var, id}, env) do
        case Env.lookup(id, env) do
            nil ->
                :error
            {_, str} ->
                {:ok, str}
        end
    end
    def eval_expr({:cons, var, atm}, env) do
        case eval_expr(var, env) do
            :error ->
                :error
        {:ok, str} ->
            case eval_expr(atm, env) do
                :error ->
                    :error
                {:ok, ts} ->
                    {:ok ,{:cons, str, ts}}
            end
        end
    end
    
    def eval_expr({:case, expr, cls}, env) do
        case eval_expr(expr, env) do
            :error ->
                :error
            {:ok, str}->
                eval_cls(cls, str, env)
        end
    end

    def eval_expr({:lambda, par, free, seq}, env) do
        case Env.closure(free, env) do
            :error ->
                :error
            closure ->
                {:ok, {:closure, ..., ..., ...}}
        end
    end


    def eval_match(:ignore, _, env) do
        {:ok, env}
    end
    def eval_match({:atm, _}, _, env ) do
        {:ok, env}
    end
    def eval_match({:var, id}, str, env) do
        case Env.lookup(id, env) do
            nil ->
                {:ok, Env.add(id, str, env)}
            {_, ^str} ->
                {:ok, env}
            {_, _} ->
                :fail
        end
    end
    def eval_match({:cons, hp, tp}, expr, env) do
        case eval_expr(expr, env) do
            :nil-> :fail
            {:ok, {:cons, hs, ts}} ->
                case eval_match(hp, hs, env) do
                    :fail ->
                        :fail
                    {:ok, env} ->
                        case eval_match(tp, ts, env) do
                            :fail -> :fail
                            {:ok, env} -> {:ok, env}
                        end
                end
        end
    end

    def eval_match(_, _, _) do
        :fail
    end

    def eval_seq([], env)do env end

    def eval_seq([exp], env) do
        eval_expr(exp, env)
    end

    def eval_seq([{:match, ptr, exp} | seq], env) do
        case eval_expr(exp, env) do
            :error ->
                :error
            {:ok, str} ->
                vars = extract_vars(ptr)
                env2 = Env.remove(vars, env)
            case eval_match(ptr, exp, env2) do
                :fail ->
                    :error
                {:ok, env} ->
                    eval_seq(seq, env)
            end
        end
    end

    def eval_cls([], _, _) do
        :error
    end

    def eval_cls([{:clause, ptr, seq}|rest], str, env) do
        cond do
            ptr == str -> eval_seq(seq, env)
            true -> eval_cls(rest, str, env)
        end
    end
    def eval(seq) do 
        eval_seq(seq, [])
    end

    def extract_vars(pattern) do
        extract_vars(pattern, [])
    end

    def extract_vars({:atm, _}, vars) do vars end
    def extract_vars(:ignore, vars) do vars end
    def extract_vars({:var, var}, vars) do
        [var | vars]
    end
    def extract_vars({:cons, head, tail}, vars) do
        extract_vars(tail, extract_vars(head, vars))
    end
end