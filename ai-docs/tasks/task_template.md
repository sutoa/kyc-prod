# AI Task Planning Template - Starter Framework

> **About This Template:** This is a systematic framework for planning and executing technical projects with AI assistance. Use this structure to break down complex features, improvements, or fixes into manageable, trackable tasks that AI agents can execute effectively.

---

## 1. Task Overview

### Task Title
<!-- Give your task a clear, specific name that describes what you're building or fixing -->
**Title:** [Brief, descriptive title - e.g., "Add User Authentication System" or "Fix Payment Integration Bug"]

### Goal Statement
<!-- Write one paragraph explaining what you want to achieve and why it matters for your project -->
**Goal:** [Clear statement of the end result you want and the business/user value it provides]

---
## 2. Strategic Analysis & Solution Options

### When to Use Strategic Analysis
<!--
AI Agent: Use your judgement to determine when strategic analysis is needed vs direct implementation.
-->

**✅ CONDUCT STRATEGIC ANALYSIS WHEN:**
- For PoC work or where there's no existing codebase
- Multiple viable technical approaches exist
- Trade-offs between different solutions are significant
- User requirements could be met through different UX patterns
- Architectural decisions will impact future development
- Implementation approach affects performance, security, or maintainability significantly
- Change touches multiple systems or has broad impact
- User has expressed uncertainty about the best approach

**❌ SKIP STRATEGIC ANALYSIS WHEN:**
- Only one obvious technical solution exists
- It's a straightforward bug fix or minor enhancement
- The implementation pattern is clearly established in the codebase
- Change is small and isolated with minimal impact
- User has already specified the exact approach they want

**DEFAULT BEHAVIOR:** When in doubt, provide strategic analysis. It's better to over-communicate than to assume.

### Problem Context
<!-- Restate the problem and why it needs strategic consideration -->
[Explain the problem and why multiple solutions should be considered — what makes this decision important?]

### Solution Options Analysis

#### Option 1: [Solution Name]
**Approach:** [Brief description of this solution approach]

**UI Design Reference:**
- **Website Model:** [Name and URL of website with similar functionality]
- **Key UI Elements:** [List specific UI patterns/elements to adopt]
- **Screenshots:** [Include screenshots or mockups demonstrating the approach]

**Pros:**
- ✅ [Advantage 1 — specific benefit]
- ✅ [Advantage 2 — quantified when possible]
- ✅ [Advantage 3 — why this is better]

**Cons:**
- ❌ [Disadvantage 1 — specific limitation]
- ❌ [Disadvantage 2 — trade-off or cost]
- ❌ [Disadvantage 3 — risk or complexity]

**Implementation Complexity:** **[Low/Medium/High]** — [Brief justification]  
**Risk Level:** **[Low/Medium/High]** — [Primary risk factors]

#### Option 2: [Solution Name]
**Approach:** [Brief description of this solution approach]

**UI Design Reference:**
- **Website Model:** [Name and URL of website with similar functionality]
- **Key UI Elements:** [List specific UI patterns/elements to adopt]
- **Screenshots:** [Include screenshots or mockups demonstrating the approach]

**Pros:**
- ✅ [Advantage 1 — specific benefit]
- ✅ [Advantage 2 — quantified when possible]
- ✅ [Advantage 3 — why this is better]

**Cons:**
- ❌ [Disadvantage 1 — specific limitation]
- ❌ [Disadvantage 2 — trade-off or cost]
- ❌ [Disadvantage 3 — risk or complexity]

**Implementation Complexity:** **[Low/Medium/High]** — [Brief justification]  
**Risk Level:** **[Low/Medium/High]** — [Primary risk factors]

#### Option 3: [Solution Name]
**Approach:** [Brief description of this solution approach]

**UI Design Reference:**
- **Website Model:** [Name and URL of website with similar functionality]
- **Key UI Elements:** [List specific UI patterns/elements to adopt]
- **Screenshots:** [Include screenshots or mockups demonstrating the approach]

**Pros:**
- ✅ [Advantage 1 — specific benefit]
- ✅ [Advantage 2 — quantified when possible]
- ✅ [Advantage 3 — why this is better]

**Cons:**
- ❌ [Disadvantage 1 — specific limitation]
- ❌ [Disadvantage 2 — trade-off or cost]
- ❌ [Disadvantage 3 — risk or complexity]

**Implementation Complexity:** **[Low/Medium/High]** — [Brief justification]  
**Risk Level:** **[Low/Medium/High]** — [Primary risk factors]

**🎯 RECOMMENDED SOLUTION:** Option **[X]** — **[Solution Name]**

**Why this is the best choice:**
1. **[Primary reason]** — [Specific justification]
2. **[Secondary reason]** — [Supporting evidence]
3. **[Additional reason]** — [Long-term considerations]

**Key Decision Factors:**
- **Performance Impact:** [How this affects app performance]
- **User Experience:** [How this affects users]
- **Maintainability:** [How this affects future development]
- **Scalability:** [How this handles growth]
- **Security:** [Security implications]

**Alternative Consideration:**
[If there’s a close second choice, explain why it wasn’t selected and under what conditions it might be preferable.]

## Decision Request

**👤 USER DECISION REQUIRED:**  
Based on this analysis, do you want to proceed with the recommended solution (Option **[X]**), or would you prefer a different approach?

**Questions for you to consider:**
- Does the recommended solution align with your priorities?
- Are there any constraints or preferences I should factor in?
- Do you have a different timeline or complexity preference?

**Next Steps:**  
Once you approve the strategic direction, I'll create the detailed implementation plan in the sections below.

---

## 3. Project Analysis & Current State

### Technology & Architecture
<!--
AI Agent: Analyze the project to fill this out.
- Check `package.json` for versions and dependencies.
- Check `tsconfig.json` for TypeScript configuration.
- Check `tailwind.config.ts` for styling and theme.
- Check `drizzle/schema/` for database schema.
- Check `middleware.ts` for authentication and routing.
- Check `components/` for existing UI patterns.
-->

- **Frameworks & Versions:** TODO: List your main frameworks and versions
- **Language:** TODO: Specify your programming language and version
- **Database & ORM:** TODO: Define your database and ORM choice
- **UI & Styling:** TODO: List your UI framework and styling approach
- **Authentication:** TODO: Specify your authentication system
- **Key Architectural Patterns:** TODO: List your main architectural patterns

### Current State
<!-- Describe what exists today - what's working, what's broken, what's missing -->
[Analysis of your current codebase state, existing functionality, and what needs to be changed]

## 4. Context & Problem Definition

### Problem Statement
<!-- This is where you clearly define the specific problem you're solving -->
[Detailed explanation of the problem, including user impact, pain points, and why it needs to be solved now]

### Success Criteria
<!-- Define exactly how you'll know when this task is complete and successful -->
- [ ] [Specific, measurable outcome 1]
- [ ] [Specific, measurable outcome 2]
- [ ] [Specific, measurable outcome 3]

---

## 4. Development Mode Context

### Development Mode Context
- **📛 IMPORTANT: This is a new application in active development**
- **No backwards compatibility concerns** — feel free to make breaking changes
- **Data loss acceptable** — existing data can be wiped/migrated aggressively
- **Users are developers/testers** — not production users requiring careful migration
- **Priority: Speed and simplicity** over data preservation
- **Aggressive refactoring allowed** — delete/recreate components as needed

### What Will Change
[If this is to add or update features in an existing codebase, list out the artifacts or the areas of the codebase that will be changed.]
- [file 1 - summary what will be changed]
- [file 2 - summary what will be changed]
- [file 3 - summary what will be changed]


---

## 5. Technical Requirements

### Functional Requirements
<!-- This is where the AI will understand exactly what the system should do - be specific about user actions and system behaviors -->
- [Requirement 1: User can…]
- [Requirement 2: System will…]
- [Requirement 3: When X happens, then Y…]

#### Examples
- Example format: "User can [specific action]"
- Example format: "System automatically [specific behavior]" 
- Example format: "When [condition] occurs, then [system response]"

### Non-Functional Requirements
<!-- This is where you define performance, security, and usability standards -->
- **Performance:** TODO: Define response time and load handling requirements
- **Security:** TODO: Specify authentication and data protection needs
- **Usability:** TODO: Set user experience and accessibility standards
- **Responsive Design:** TODO: Define mobile, tablet, desktop support requirements
- **Theme Support:** TODO: Specify light/dark mode and brand requirements
- **UI Design References:** TODO: List specific websites/applications to model the UI after, including:
  - Screenshots of similar functionality in production applications
  - Links to reference implementations
  - Specific UI patterns to adopt or avoid
  - Design system guidelines if applicable

### Technical Constraints
<!-- This is where you list limitations the AI agent must work within -->
- [Must use existing system X]
- [Cannot modify database table Y]
- [Must maintain compatibility with feature Z]

---

## 6. Data & Database Changes

### Database Schema Changes
<!-- This is where you specify any database modifications needed -->

TODO: Add your SQL schema changes here (new tables, columns, indexes, etc.)

### Data Model Updates
<!-- This is where you define TypeScript types, schema updates, or data structure changes -->

TODO: Define your TypeScript types, interfaces, and data structure changes

### Data Migration Plan
<!-- This is where you plan how to handle existing data during changes -->

TODO: Plan your data migration steps (backup, apply changes, transform data, validate)

---

## 7. API & Backend Changes

### Data Access Pattern Rules
<!-- This is where you tell the AI agent how to structure backend code in your project -->

TODO: Define where different types of code should go in your project (mutations, queries, API routes)

### Server Actions
<!-- List the backend mutation operations you need -->

TODO: List your create, update, delete operations and what they do

### Database Queries
<!-- Specify how you'll fetch data -->

TODO: Define your data fetching approach (direct queries vs separate functions)

---

## 8. Frontend Changes

### New Components
<!-- This is where you specify UI components to be created -->

TODO: List the new components you need to create and their purpose

### Page Updates
<!-- This is where you list pages that need modifications -->

TODO: List the pages that need changes and what modifications are required

### State Management
<!-- This is where you plan how data flows through your frontend -->

TODO: Define your state management approach and data flow strategy

---

## 9. Implementation Plan

TODO: Break your work into phases with specific tasks and file paths

---

## 10. Task Completion Tracking

### Real-Time Progress Tracking
<!-- This is where you tell the AI agent to update progress as work is completed -->

TODO: Define how you want the AI to track and report progress on tasks

---

## 11. File Structure & Organization

TODO: Plan what new files to create and existing files to modify

---

## 12. AI Agent Instructions

### Implementation Workflow
<!-- This is where you give specific instructions to your AI agent -->
🎯 **MANDATORY PROCESS:**
TODO:

### Communication Preferences
<!-- This is where you set expectations for how the AI should communicate -->
TODO: How do you want the agent to communicate with you

### Code Quality Standards
<!-- This is where you define your coding standards for the AI to follow -->
TODO: Any specific code standards

---

## 13. Second-Order Impact Analysis

### Impact Assessment
<!-- This is where you think through broader consequences of your changes -->

TODO: Tell the AI what sections of code you're worried about breaking, performance concerns, and user workflow impacts

---
