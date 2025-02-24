import React, { createContext, useState, ReactNode } from 'react';

export const AuthContext = createContext<{
    isLoggedIn: boolean;
    userId: number;
    setLoggedIn: (loggedIn: boolean) => void;
    setUserId: (loggedIn: number) => void;
    token: string;
    setToken: (loggedIn: string) => void;
}>({
    isLoggedIn: false,
    userId: 0,
    setUserId: () => {},
    setLoggedIn: () => {},
    token:"",
    setToken: () => {},
});

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userId, setUserId] = useState(0);
    const [token, setToken] =useState("");

    const setLoggedIn = (loggedIn: boolean) => {
        setIsLoggedIn(loggedIn);
    }

    return (
        <AuthContext.Provider value={{ isLoggedIn, userId, setLoggedIn, setUserId, token, setToken}}>
            {children}
        </AuthContext.Provider>
    );
};