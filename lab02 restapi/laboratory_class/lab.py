from fastapi import FastAPI, HTTPException, status, Body
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field
from typing import List, Optional
from datetime import datetime
from uuid import uuid4

app=FastAPI( )

polls = {}  # /poll

def generate_id():
    return datetime.now().isoformat()

class Vote(BaseModel):
    id: str = Field(default_factory=generate_id)  # /poll/{poll_id}/vote/{vote_id}


class Poll(BaseModel):  # /poll/{poll_id}
    id: str = Field(default_factory=generate_id)
    votes: List[Vote] = [] 

class PartialPoll(BaseModel):
    id: Optional[str] = None
    votes: List[Vote] = [] 


@app.get("/poll",status_code=status.HTTP_200_OK, response_model=List[Poll])
async def get_polls(skip: int = 0, limit: int = 10):
    return list(polls.values())[skip: skip + limit]

@app.post("/poll", status_code=status.HTTP_201_CREATED, response_model=Poll)
async def create_poll():
    new_poll = Poll()
    polls[new_poll.id] = new_poll
    return new_poll

@app.get("/poll/{poll_id}", status_code=status.HTTP_200_OK, response_model=Poll)
async def get_poll(poll_id: str):
    if poll_id in polls:
        return polls[poll_id]
    raise HTTPException(status_code=404, detail="Poll not found")   

@app.put("/poll/{poll_id}", status_code=status.HTTP_200_OK, response_model=Poll)
async def update_poll(poll_id: str, poll: PartialPoll):
    if poll_id in polls:
        if not poll.votes:
            polls[poll_id].votes = []
        else:
            
            vote_ids = [vote.id for vote in poll.votes]
            if len(vote_ids) != len(set(vote_ids)):
                raise HTTPException(status_code=400, detail="Vote ids must be unique")
            
            polls[poll_id].votes = poll.votes

        return polls[poll_id]
    raise HTTPException(status_code=404, detail="Poll not found")



@app.delete("/poll/{poll_id}", status_code=status.HTTP_204_NO_CONTENT, response_class=JSONResponse)
async def delete_poll(poll_id: str):
    if poll_id in polls:
        del polls[poll_id]
        return {"message": "Poll deleted successfully"}
    raise HTTPException(status_code=404, detail="Poll not found")


@app.get("/poll/{poll_id}/vote", status_code=status.HTTP_200_OK)
async def get_votes(poll_id: str, skip: int = 0, limit: int = 10):
    if poll_id not in polls:
        raise HTTPException(status_code=404, detail="Poll not found")
    votes = polls[poll_id].votes[skip: skip + limit]
    return votes

@app.post("/poll/{poll_id}/vote", status_code=status.HTTP_201_CREATED, response_model=Vote)
async def create_vote(poll_id: str, vote: Vote):
    if poll_id in polls:
        vote_ids = set([v.id for v in polls[poll_id].votes])
        if vote.id in vote_ids:
            raise HTTPException(status_code=400, detail="Vote id must be unique")
        polls[poll_id].votes.append(vote)
        return vote
    raise HTTPException(status_code=404, detail="Poll not found")




@app.get("/poll/{poll_id}/vote/{vote_id}", response_model=Vote)
async def get_vote(poll_id: str, vote_id: str):
    if poll_id in polls:
        poll = polls[poll_id]
        for vote in poll.votes:
            if vote.id == vote_id:
                return vote
    raise HTTPException(status_code=404, detail="Vote not found")

@app.put("/poll/{poll_id}/vote/{vote_id}", response_model=Vote)
async def update_vote(poll_id: str, vote_id: str, vote_data: Vote = Body(...)):
    if poll_id in polls:
        poll = polls[poll_id]
        for index, vote in enumerate(poll.votes):
            if vote.id == vote_id:
                poll.votes[index] = vote_data
                return vote_data
        vote_ids = set([v.id for v in polls[poll_id].votes])
        if vote_data.id in vote_ids:
            raise HTTPException(status_code=400, detail="Vote id must be unique")
        poll.votes.append(vote_data)
        return vote_data
                
    raise HTTPException(status_code=404, detail="Vote not found")


@app.delete("/poll/{poll_id}/vote/{vote_id}", status_code=status.HTTP_200_OK)
async def delete_vote(poll_id: str, vote_id: str):
    if poll_id in polls:
        poll = polls[poll_id]
        ind = -1
        for index, vote in enumerate(poll.votes):
            if vote.id == vote_id:
                ind = index
                break
        if ind != -1:
            del poll.votes[ind]
            return {"message": "Vote deleted successfully"}
    raise HTTPException(status_code=404, detail="Vote not found")


# python -m uvicorn distributed:app --reload