from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import Dict

app = FastAPI(title="Account Service")

# In-memory database (for demo)
accounts: Dict[int, dict] = {}


class Account(BaseModel):
    id: int
    name: str
    balance: float


@app.get("/")
def home():
    return {"message": "Account Service is running"}


@app.get("/health")
def health():
    return {"status": "healthy"}


@app.post("/accounts")
def create_account(account: Account):
    if account.id in accounts:
        raise HTTPException(status_code=400, detail="Account already exists")
    accounts[account.id] = account.dict()
    return {"message": "Account created", "account": account}


@app.get("/accounts/{account_id}")
def get_account(account_id: int):
    if account_id not in accounts:
        raise HTTPException(status_code=404, detail="Account not found")
    return accounts[account_id]


@app.get("/accounts")
def list_accounts():
    return accounts
